import { FetchAPI } from './fetch-api.js';
import { Config } from './config.js';

export class ShoppingCartAPI {

    static SHOPPING_CART_URL = Config.SERVER_URL + '/carts';

    static addCartItem(productId) {
        return FetchAPI.post(ShoppingCartAPI.SHOPPING_CART_URL + '/add-cart-item/' + productId)
    }

    static removeCartItem(productId) {
        return FetchAPI.post(`${ShoppingCartAPI.SHOPPING_CART_URL}/remove-cart-item/${productId}`)
    }

    static getAll() {
        return FetchAPI.get(ShoppingCartAPI.SHOPPING_CART_URL);
    }

    static placeOrder() {
        return FetchAPI.post(`${ShoppingCartAPI.SHOPPING_CART_URL}/place-order`)
    }
}
