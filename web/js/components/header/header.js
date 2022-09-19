export class Header {

    render(component, data = {}) {
        let nav = `
            <header class="py-5 bg-light border-bottom mb-4">
                 <div class="container">
                    <div class="text-center my-5">
                        <h1 class="fw-bolder">Welcome to my store</h1>
                    </div>
                </div>
            </header>
        `;
        document.getElementById('header').innerHTML = nav;
        if(component) {
            component.render(data);
        }
    }

    remove() {
        document.getElementById('header').innerHTML = '';
    }
}