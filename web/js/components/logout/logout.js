import { Util } from "../../modules/util.js";

export class Logout {

    render(user) {
        let logout = `
             <lable class="welcome-user">Welcome, ${user.email}</lable>
             <button class="btn btn-primary btn-logout" id="btn-logout">Logout</button>
        `;
        document.getElementById('login').innerHTML = logout;
        let btnLogout = document.getElementById('btn-logout');
        if(btnLogout) {
            btnLogout.addEventListener('click', function () {
                Util.removeAccessToken();
                Util.renderApplication();
            })
        }
    }
}