require("dotenv").config();
const express = require('express')
const cors = require("cors");
const axios = require("axios");

const app = express()
const port = 4000

app.use(express.json()); // 클라이언트가 JSON 형식의 데이터를 요청할 때, 이를 자동으로 파싱해서 req.body로 접근할 수 있도록 설정
app.use(cors());

// app.use(
//   cors({
//     origin: "https://my-firstorganization.github.io",
//     methods: ["POST"],
//     allowedHeaders: ["Content-Type"],
//   })
// );

app.post('/', async (req, res) => {
  const { TOGETHER_API_KEY, GROQ_API_KEY } = process.env;
  const TOGETHER_BASE_URL = "https://api.together.xyz";
  const GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";
  const GROQ_LLAMA_MODEL = "llama3-70b-8192";
  const MIXTRAL_MODEL = "mixtral-8x7b-32768";
  const FLUX_MODEL = "black-forest-labs/FLUX.1-schnell-Free";
  const DEEPSEEK_MODEL = "deepseek-ai/DeepSeek-R1-Distill-Llama-70B-free";

  async function callAI({
    url,
    model,
    text,
    textForImage,
    apiKey, 
    jsonMode = false,
    max_tokens,
  }) {
    const payload = {
        model,
    }
    if(max_tokens){
        payload.max_tokens = max_tokens;
    }
    if(text){
        payload.messages = [
            {
                role: "user",
                content: text,
            },
        ];
    }
    if(textForImage){
        payload.prompt = textForImage;
    }
    if(jsonMode){
        payload.response_format = {type:"json_object"}
    }
    const response = await axios.post(url, payload, {
        headers: {
            Authorization: `Bearer ${apiKey}`,
            "Content-Type": "application/json"
        },
    });
    return response.data;
  }
  
  const { text } = req.body;
  const prompt = await callAI({
    url: GROQ_URL,
    apiKey: GROQ_API_KEY,
    model: GROQ_LLAMA_MODEL,
    text: `${text}를 바탕으로 너가 어울리는 ai 이미지 생성을 위한 200자 이내의 영어 프롬포트를 작성해줘`
  }).then((res) => res.choices[0].message.content);

  const promptJSON = await callAI({
    url: GROQ_URL,
    apiKey: GROQ_API_KEY,
    model: MIXTRAL_MODEL,
    text: `${prompt}에서 ai 이미지 생성을 위해 작성된 프롬프트를 JSON Object로 prompt라는 key로 JSON string으로 output해줘`,
    jsonMode: true,
  }).then((res) => JSON.parse(res.choices[0].message.content).prompt);

  const image = await callAI({
    url: `${TOGETHER_BASE_URL}/v1/images/generations`,
    apiKey: TOGETHER_API_KEY,
    model: FLUX_MODEL,
    text: promptJSON,
  }).then((res) => res.data[0].url);

  const prompt2 = await callAI({
    url: GROQ_URL,
    apiKey: GROQ_API_KEY,
    model: GROQ_LLAMA_MODEL,
    text: `${text}를 바탕으로 해당 종류 스터디를 위해서 준비해야 할 것과, 단기 커리큘럼을 위한 200자 이내의 한글 프롬프트를 작성해줘`,
  }).then((res) => res.choices[0].message.content);

  const promptJSON2 = await callAI({
    url: GROQ_URL,
    apiKey: GROQ_API_KEY,
    model: MIXTRAL_MODEL,
    text: `${prompt2}에서 reasoning을 위해 작성된 200자 이내의 한글 프롬프트를 JSON Object로 prompt라는 key로 JSON string으로 output해줘`,
    jsonMode: true,
  }).then((res) => JSON.parse(res.choices[0].message.content).prompt);

  const desc = await callAI({
    url: `${TOGETHER_BASE_URL}/v1/chat/completions`,
    apiKey: TOGETHER_API_KEY,
    model: DEEPSEEK_MODEL,
    text: `${promptJSON2}을 기반으로 마크다운 문법 없이 한글 결과문을 원하고, 엔터로 줄바꿈을 넣어줘`,
    max_tokens: 2048,
  }).then((res) => res.choices[0].message.content.split("</think>")[1]);
  
  res.json({
      image,
      desc
  })
})


app.listen(port, () => {
  console.log(`app listening on port ${port}`)
})