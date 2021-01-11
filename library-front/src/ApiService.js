import React from 'react'

class API {

    static async getBook(id) {
        let result = await fetch('/api/book/' + id)
            .then(result => result.json())
            .then((result) => {
                let authors = ""
                result.authors.forEach(a => authors += a.name + ", ")
                authors = authors.slice(0, authors.length - 2)
                let genres = ""
                result.genres.forEach(g => genres += g.name + ", ")
                genres = genres.slice(0, genres.length - 2)
                return {
                    id: result.id,
                    caption: result.caption,
                    authors: authors,
                    genres: genres
                }
            })
        return result
    }

    static async getBooks() {
        let books = await fetch('/api/book/')
            .then(result => result.json())
            .then((result) => {
                return result
            })
        return books
    }

    static async saveBook(book, requiredMethod) {
        let response = fetch('/api/book/', {
            method: requiredMethod,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        })
            .then(result => {
                return result.json()
            })
        return response
    }

    static async deleteBook(id) {
        let result = await fetch('/api/book/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((result) => {
            return true
        })
        return result
    }
}


export default API;
