const API_URL = 'http://localhost:8080/api/v1';

class Api {
    // Auth related methods
    static async register(userData) {
        const response = await fetch(`${API_URL}/auth/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData)
        });
        return await response.json();
    }

    static async verifyRegistration(email, code) {
        const response = await fetch(`${API_URL}/auth/verification`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, code })
        });
        return await response.json();
    }

    static async login(email) {
        const response = await fetch(`${API_URL}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email })
        });
        return await response.json();
    }

    static async confirmLogin(email, code) {
        const response = await fetch(`${API_URL}/auth/confirm-login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, code })
        });
        return await response.json();
    }

    // Products related methods
    static async getProducts(page = 0, size = 10) {
        const response = await fetch(`${API_URL}/product/get?page=${page}&size=${size}`);
        return await response.json();
    }

    static async getFreeProducts(page = 0, size = 10) {
        const response = await fetch(`${API_URL}/e-free/get?page=${page}&size=${size}`);
        return await response.json();
    }

    static async createProduct(productData, isFree = false) {
        const endpoint = isFree ? '/e-free/create' : '/product/create';
        const response = await fetch(`${API_URL}${endpoint}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(productData)
        });
        return await response.json();
    }

    // Category related methods
    static async getCategories() {
        const response = await fetch(`${API_URL}/category/get-all`);
        return await response.json();
    }

    // Location related methods
    static async createLocation(locationData) {
        const response = await fetch(`${API_URL}/location/create`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(locationData)
        });
        return await response.json();
    }

    // Photo related methods
    static async uploadPhoto(productId, file) {
        const formData = new FormData();
        formData.append('file', file);
        
        const response = await fetch(`${API_URL}/photo/upload?productId=${productId}`, {
            method: 'POST',
            body: formData
        });
        return await response.json();
    }

    static async getPhotosByProductId(productId) {
        const response = await fetch(`${API_URL}/photo/get-by-product-id/${productId}`);
        return await response.json();
    }
}

// Auth token management
const TOKEN_KEY = 'auth_token';

function setAuthToken(token) {
    localStorage.setItem(TOKEN_KEY, token);
}

function getAuthToken() {
    return localStorage.getItem(TOKEN_KEY);
}

function removeAuthToken() {
    localStorage.removeItem(TOKEN_KEY);
}

// Export the API class and token management functions
export { Api, setAuthToken, getAuthToken, removeAuthToken };