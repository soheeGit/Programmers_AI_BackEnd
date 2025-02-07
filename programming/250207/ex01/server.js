const path = require("path");

const axios = require("axios");

const fastify = require("fastify")({
  logger: true, //로그를 자세히 알려줌
});

fastify.register(require("@fastify/cors"), {
  origin: "*", // 모든 도메인에서 요청을 허용
});

async function makeReply(text, model) {
  // const GEMINI_API_KEY = "";
  const url = `https://generativelanguage.googleapis.com/v1beta/models/${model}:generateContent?key=${process.env.GEMINI_API_KEY}`;
  const response = await axios({
    url, //단축: url-> url
    method: "POST",
    data: {
      contents: [
        {
          parts: [
            {
              text: `너는 20년 경력의 책 전문가야. {${text}}의 메시지를 바탕으로, 책을 추천해주고, 50자 이내에 평문으로 한국어로 작성해줘.`,
            },
          ],
        },
      ],
    },
    headers: {
      "Content-Type": "application/json",
    },
  });
  // TODO
  const json = await response.data;
  return json.candidates[0].content.parts[0].text;
}
// 2차 deepseek
async function makeReply2(content, model = "deepseek-r1-distill-llama-70b") {
  // const GROQ_API_KEY = "";
  const url = "https://api.groq.com/openai/v1/chat/completions";
  const response = await axios({
    url,
    method: "POST",
    data: {
      messages: [
        {
          role: "user",
          content: `너는 20년 경력의 책 전문가야. {${content}}를 바탕으로, 책을 추천해주고 50자 이내인  한국어로 작성해줘.`,
        },
      ],
      model,
    },
    headers: {
      Authorization: `Bearer ${process.env.GROQ_API_KEY}`,
      "Content-Type": "application/json",
    },
  });
  const json = await response.data;
  return json.choices[0].message.content.split("</think>")[1].trim(); //앞뒤에 공백을 잘라줌
}

fastify.post("/", function (request, reply) {
  const { body } = request;
  // return "Hello Glitch";
  return reply.send({ body });
});

fastify.post("/1", async function (request, reply) {
  const { body } = request;
  const { text, model } = request.body;
  // return "Hello Glitch";
  return reply.send({ reply: await makeReply(text, model) });
});

fastify.post("/2", async function (request, reply) {
  const { body } = request;
  const { text } = body;
  // return "Hello Glitch";
  return reply.send({ reply: await makeReply2(text) });
});

fastify.listen(
  { port: process.env.PORT, host: "0.0.0.0" },
  function (err, address) {
    if (err) {
      console.error(err);
      process.exit(1);
    }
    console.log(`Your app is listening on ${address}`);
  }
);
