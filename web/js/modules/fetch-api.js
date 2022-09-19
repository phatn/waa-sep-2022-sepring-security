import { Util } from "./util.js";

export class FetchAPI {

    static async post(url = '', data = {}) {
        let headers = { 'Content-Type': 'application/json', 'Authorization': `Bearer ${Util.getAccessToken()}` };
        if(url.includes('/uaa')) {
            headers = { 'Content-Type': 'application/json'};
        }
        const response = await fetch(url, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(data)
        });
        console.log(JSON.stringify(response));
        return response.json();
    }

    static async get(url = '') {
        const response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${Util.getAccessToken()}` },
        });
        return response.json();
    }

    static async delete(url = '', data = {}) {
        const response = await fetch(url, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${Util.getAccessToken()}` },
            body: JSON.stringify(data)
        });
        return response.json();
    }

    static async put(url = '', data = {}) {
        const response = await fetch(url, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${Util.getAccessToken()}` },
            body: JSON.stringify(data)
        });
        return response.json();
    }
}