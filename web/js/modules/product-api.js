import { FetchAPI } from './fetch-api.js';
import { Config } from './config.js';

export class ProductAPI {

    static PRODUCT_URL = Config.SERVER_URL + '/products';

    static getAll() {
        return FetchAPI.get(ProductAPI.PRODUCT_URL);
    }
}
