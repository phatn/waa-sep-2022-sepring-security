import { UserAPI } from "../../modules/user-api.js";
import { ErrorAlert } from "../alert/error-alert.js";
import { Header } from "../header/header.js";
import { Util } from "../../modules/util.js";
import { Nav } from "../nav/nav.js";
import { Logout } from "../logout/logout.js";

export class Login {

    render() {
        let login = `
            <div>
                <div class="form-item">
                    <label for="email">Email: </label>
                    <input id="email" type="text" name="email" class="required">
                </div>
                <div class="form-item">
                    <label for="password">Password: </label>
                    <input id="password" type="password" name="password" class="required">
                </div>
            </div>
            <button id="btn-login" class="btn btn-primary btn-login">Login</button>
            <button id="btn-register" class="btn btn-primary btn-login">Register</button>
        `;
        document.title = 'MyStore - Login';
        document.getElementById('login').innerHTML = login;
        document.getElementById('email').focus();

        let btnLogin = document.getElementById('btn-login');
        btnLogin.addEventListener('click', e => {
            e.preventDefault();
            if(Login.validate()) {
                Login.authenticate();
            }
        })

        let passwordElement = document.getElementById('password');
        passwordElement.addEventListener('keypress', function(event) {
            if (event.key === "Enter") {
                event.preventDefault();
                if(Login.validate()) {
                    Login.authenticate();
                }
            }
        })
    }

    static authenticate() {
        let email = document.getElementById('email').value;
        let password = document.getElementById('password').value;

        UserAPI.authenticate({email, password }).then(
            result => {
                if(result.accessToken) {
                    Util.setAccessToken(result.accessToken);
                    new Nav().render(new Logout(), result);
                    new Header().remove();
                    Util.renderProductList();

                } else {
                    new ErrorAlert().render(result.errorAuth);
                }
            }
        );
    }

    static validate() {
        const requiredFields = document.querySelectorAll('#login input[class~="required"]');
        let valid = true;
        for(let i = 0; i < requiredFields.length; i++) {
            let input = requiredFields[i];
            if(input.value === '') {
                input.classList.add("error");
                valid =  false;
            } else {
                input.classList.remove("error");
            }
        }
        return valid;
    }
}