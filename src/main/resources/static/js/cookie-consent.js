document.addEventListener("DOMContentLoaded", function () {
    const banner = document.getElementById("cookie-banner");
    const acceptButton = document.getElementById("accept-cookies");

    if (!banner || !acceptButton) {
        return;
    }

    const consentCookie = getCookie("cookie_consent");

    if (consentCookie !== "accepted") {
        banner.classList.remove("hidden");
    }

    acceptButton.addEventListener("click", function () {
        setCookie("cookie_consent", "accepted", 365);
        banner.classList.add("hidden");
    });
});

function setCookie(name, value, days) {
    const expires = new Date();
    expires.setTime(expires.getTime() + (days * 24 * 60 * 60 * 1000));

    document.cookie =
        name + "=" + encodeURIComponent(value) +
        ";expires=" + expires.toUTCString() +
        ";path=/";
}

function getCookie(name) {
    const cookieName = name + "=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const cookies = decodedCookie.split(";");

    for (let i = 0; i < cookies.length; i++) {
        let cookie = cookies[i].trim();

        if (cookie.indexOf(cookieName) === 0) {
            return cookie.substring(cookieName.length, cookie.length);
        }
    }

    return null;
}