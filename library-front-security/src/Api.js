class Api {
    static async getAuth(_token) {
        return fetch('/api/auth/', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Cookie': "JSESSIONID=" + _token
            }
        })
            .then( result => {
                if(result.status===401){
                    return {username:null,authenticated:false,roles:[],token:null}
                } else if(result.status===200){
                    return result.json()
                }
                return {username:null,authenticated:null,roles:[],token:null}
            })
    }

    static async login( _username, _password ){
        return fetch('/login', {
            method: 'POST',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: "username="+_username+"&password="+_password
        })
            .then( result => {
                if(result.status===401){
                    return null
                } else if(result.status===200){
                    return result.headers.get("authorization")
                }
                return null
            })
    }

    static async getBooks(_token) {
        return fetch('/api/book/', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Cookie': "JSESSIONID=" + _token
            }
        })
            .then( result => {
                if(result.status===200) {
                    return result.json()
                } else {
                    return []
                }
            } )
    }

    static async getBook(_token, _id) {
        return fetch('/api/book/'+_id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Cookie': "JSESSIONID=" + _token
            }
        })
            .then( result => {
                if(result.status===200) {
                    return result.json()
                } else {
                    return { id:null,caption:null,authors:[],genres:[] }
                }
            } )
    }

    static async saveBook(_token, _book, _requiredMethod) {
        let response = fetch('/api/book/', {
            method: _requiredMethod,
            headers: {
                'Content-Type': 'application/json',
                'Cookie': "JSESSIONID="+_token
            },
            body: JSON.stringify(_book)
        })
            .then(result => {
                return result.json()
            })
        return response
    }

    static async deleteBook(_token, _id) {
        return fetch('/api/book/'+_id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Cookie': "JSESSIONID=" + _token
            }
        })
            .then(result => {
                return result.status===200
            })
    }

    static async logout(_token) {
        return fetch('/logout', {
            method: 'GET',
            headers: {
                'Cookie': "JSESSIONID=" + _token
            }
        })
            .then(result => {
                return result.status===200
            })
    }
}

export default Api
