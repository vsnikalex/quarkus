if (!!window.EventSource) {
    let eventSource = new EventSource("/stream/Quarkus");
    eventSource.onmessage = function (event) {
        let container = document.getElementById("container");
        let paragraph = document.createElement("p");
        paragraph.innerHTML = event.data;
        container.appendChild(paragraph);
    };
} else {
    window.alert("EventSource not available on this browser.")
}