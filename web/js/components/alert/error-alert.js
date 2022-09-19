import { Config } from "../../modules/config.js";

export class ErrorAlert {

    render(message = '') {
        let nav = `
            <div class="alert-popup-container">
                 <div class="alert alert-danger alert-dismissible fade show alert-popup">
                    ${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </div>
        `;
        let notificationElement = document.getElementById('notification');
        notificationElement.innerHTML = nav;
        window.setTimeout(function() {
            notificationElement.innerHTML = ''
        }, Config.ALERT_TIME_OUT);
    }

    remove() {
        document.getElementById('notification').innerHTML = '';
    }
}