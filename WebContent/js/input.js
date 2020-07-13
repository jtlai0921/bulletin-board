(() => {
    document.querySelector(".cancel").addEventListener("click", () => window.location.href = "./index.html");
    document.querySelector(".submit").addEventListener("click", () => {
        const name = document.querySelector("#name").value.trim();
        const content = document.querySelector("#content").value.trim();
        const now = new Date();
        const createTime = `${now.getFullYear()}/${now.getMonth()+1}/${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`;
        const errorMsg = validate(name, content);
        if (errorMsg) {
            alert(`嘿！\n${errorMsg}`);
            return;
        } else {
            fetch("http://localhost:8080/bulletin-board/BulletinBoard", {
                method: "POST",
                headers: { "Content-type": "application/json;charset=utf-8" },
                body: JSON.stringify({ name, content, createTime })
            }).then(res => {
                if (res.ok) {
                    document.querySelector(".board").innerHTML = `
                                <h2>留言成功囉！\n 將自動導回首頁...</h2>
                            `
                    window.setTimeout(() => window.location.href = "./index.jsp", 3000)
                }
            })

        }
    })

    const validate = (name, content) => {
        let errorMsg = "";
        if (!name) {
            errorMsg += "名稱欄位不得為空\n";
        }
        if (!content) {
            errorMsg += "留言欄位不得為空\n";
        }
        return errorMsg;
    }
})()

