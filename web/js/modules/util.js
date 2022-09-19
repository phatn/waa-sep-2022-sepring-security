import { Config } from "./config.js";
import { ProductAPI } from "./product-api.js";
import { ProductList } from "../components/product/product-list.js";
import { ShoppingCartAPI}  from "./shopping-cart-api.js";
import { ShoppingCart } from "../components/shoppingCart/shopping-cart.js";
import { Nav } from "../components/nav/nav.js";
import { Logout } from "../components/logout/logout.js";
import { Header } from "../components/header/header.js";
import { Login } from "../components/login/login.js";

export class Util {

    static setAccessToken = (accessToken) => localStorage.setItem(Config.ACCESS_TOKEN_NAME, accessToken);

    static removeAccessToken = () => localStorage.removeItem(Config.ACCESS_TOKEN_NAME)

    static getAccessToken = () => localStorage.getItem(Config.ACCESS_TOKEN_NAME)

    static getSessionUsername() {
        let accessToken = this.getAccessToken();
        if(accessToken && accessToken.split('_').length > 0) {
            return accessToken.split('_')[0];;
        }

        return '';
    }

    static renderProductList() {
        ProductAPI.getAll().then(products => {
            let productList = new ProductList(products);
            productList.render();
        })
    }

    static removeProductList() {
        new ProductList(null).render();
    }

    static renderShoppingCart() {
        ShoppingCartAPI.getAll().then(cart => {
            let shoppingCart = new ShoppingCart(cart);
            shoppingCart.render();
        })
    }

    static removeShoppingCart() {
        document.getElementById('shopping-cart').innerHTML = '';
    }

    static toTitleCase(str) {
        if(!str) {
            return '';
        }

        const lowerStr = str.toLowerCase();
        return str.charAt(0).toLocaleUpperCase() + lowerStr.slice(1);
    }

    static renderApplication() {
        let sessionId = Util.getAccessToken();
        if(sessionId) {
            new Nav().render(new Logout(), {
                name: Util.toTitleCase(Util.getSessionUsername())
            });

            new Header().remove();
            Util.renderProductList();
            Util.renderShoppingCart();
        } else {
            new Nav().render(new Login());
            new Header().render();
            Util.removeProductList();
            Util.removeShoppingCart();
        }
    }

}