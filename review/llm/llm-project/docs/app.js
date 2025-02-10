async function main() {
    async function handleCC(event) {
        event.preventDefault();

        const spinner = document.createElement("div");
        spinner.classList.add("spinner-border");
        document.querySelector("#box").appendChild(spinner);

        const url = "https://mesquite-leaf-fiber.glitch.me";
        const formData = new FormData(document.querySelector("#ccForm"));
        const text = formData.get("text");
        const response = await fetch(url, {
            method: "POST",
            body: JSON.stringify({
                text,
            }),
            headers: {
                "Content-Type" : "application/json",
            }
        })
        const json = await response.json();
        spinner.remove();
        
        // document.querySelector("#box").textContent = JSON.stringify(json);

        const {image, desc} = json;

        const box = document.querySelector("#box");
        box.innerHTML = "";
        const imageTag = document.createElement("img");
        imageTag.classList.add("img-fluid");
        imageTag.src = image;
        const descTag = document.createElement("p");
        descTag.textContent = desc;
        box.appendChild(imageTag);
        box.appendChild(descTag);
    }
    document.querySelector("#ccForm").addEventListener("submit", handleCC);
}

document.addEventListener("DOMContentLoaded", main);