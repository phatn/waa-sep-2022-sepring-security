import { Config } from "../../modules/config.js";
import { ShoppingCartAPI } from "../../modules/shopping-cart-api.js";
import { ShoppingCart } from "../shoppingCart/shopping-cart.js";
import { Util } from "../../modules/util.js";
import { ErrorAlert } from "../alert/error-alert.js";

export class ProductList {

    constructor(products) {
        this.products = products;
    }

    render() {
        let productList = '';
        let rows = '';

        if(this.products) {
            this.products.forEach((prod, index) => {
                let disabledBtnCart = prod.stock <= 0 ? 'btn-cart-disabled' : '';
                rows += `
                <tr>
                    <th scope="row">${index + 1}</th>
                    <td>${prod.name}</td>
                    <td>${prod.price}</td>
                    <td><img class="prod-image" src="${Config.SERVER_URL}${prod.image}"/></td>
                    <td>${prod.stock}</td>
                    <td><i class="bi-cart-plus cart-icon btn-cart ${disabledBtnCart}" data-product-id="${prod.id}"></i></td>
                </tr>
            `;
            });

            productList = `
            <div class="product-list shadow-lg">
                <div class="lead mb-0"><p>Product List</p><p><button id="btn-add-product" class="btn btn-primary btn-login">Add</button></p></div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Image</th>
                        <th scope="col">Stock</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    ${rows}
                    </tbody>
                </table>
            </div>
        `;
        }

        document.getElementById('product-list').innerHTML = productList;
        let buttonCarts = document.getElementsByClassName('btn-cart');
        if(buttonCarts) {
            Array.prototype.forEach.call(buttonCarts, function(buttonCart) {
                buttonCart.addEventListener('click', function() {
                    ShoppingCartAPI.addCartItem(this.dataset.productId).then(data => {
                        if(data.errorAuth) {
                            Util.renderApplication();
                        } else if(data.error) {
                            new ErrorAlert().render(data.error);
                        } else {
                            let shoppingCart = new ShoppingCart(data);
                            shoppingCart.render();
                        }
                    });
                })
            });
        }


        let passwordElement = document.getElementById('btn-add-product');
        passwordElement.addEventListener('click', function(event) {
            console.log('Add product....');
        })

    }
}