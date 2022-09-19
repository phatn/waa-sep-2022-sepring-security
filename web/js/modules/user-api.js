import { FetchAPI } from './fetch-api.js';
import { Config } from './config.js';
export class UserAPI {

    static USER_URL = Config.SERVER_URL + '/uaa';

    static authenticate(user) {
        return FetchAPI.post(UserAPI.USER_URL, user);
    }

    static register(user) {
        return FetchAPI.post(UserAPI.USER_URL + '/sign-up', user);
    }
}
