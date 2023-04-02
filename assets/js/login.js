function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    user_ASCII = btoa(username + ":" + password);
    console.log(user_ASCII);

    let requestConfiq = {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            'Authorization': "Basic " + user_ASCII
        }
    }

    fetch("http://localhost:8080/api/students", requestConfiq)
        .then((response) => {
            if (response.ok) {
                window.location.href = "index.html";
                // Set the password
                localStorage.setItem("password", user_ASCII);

            }
            else {
                alert("Uncorrect username or password");
            }
        });

}
