(() => {
    fetch("http://localhost:8080/bulletin-board/BulletinBoard", {
        method: "GET",
        headers: { "Content-type": "application/json;charset=utf-8" }
    }).then(res => {
        if (res.ok) return res.json();
    }).then(data => {
        if (data.length > 0) {
            data.forEach(it => {
            	const dom = parseHtml(msgBlockStr);
                dom.querySelector(".authorName").innerText = it.name;
                dom.querySelector(".createTime").innerText = it.createTime;
                dom.querySelector(".content").innerText = it.content;
                document.querySelector(".board").appendChild(dom);
            })
       } else {
//            document.querySelector(".board").innerHTML = `
////                    <h2>Ooops...都還沒有人來留言</h2>
//                `
        }
    }).catch(err => {
        console.log(err);
        document.querySelector(".board").innerHTML = `
                    <h2>empty var1</h2>
                `
    });

    document.querySelector(".redirect").addEventListener("click", () => window.location = "./input.jsp");

    const parseHtml = (html, parent) => {
        if (!parent) {
            parent = document.body;
        }
        let range = document.createRange();
        range.selectNode(parent);
        let result = range.createContextualFragment(html).firstElementChild;
        range.detach();
        return result;
    }
    
    const msgBlockStr = `
    <div class="mt10 msg-block m5">
        <hr>
        <div>
            <span class="authorName"></span>於
            <span class="createTime"></span>說：
        </div>
        <hr>
        <div class="content"></div>
        <hr>
    </div>
    `;
}) ();