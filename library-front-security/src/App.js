import React from 'react'
import Api from './Api'
import BookItem from './BookItem'
import BookEntity from "./BookEntity";
import './App.css'

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user: {
                authenticated: false,
                username: null,
                roles: [],
                token: null
            },
            books: [],
            refresh: false,
            edit: {
                id: null,
                caption: null,
                authors: [],
                genres: []
            },
            error:null
        }
        this.checkAuth = this.checkAuth.bind(this)
        this.login = this.login.bind(this)
        this.loadBooks = this.loadBooks.bind(this)
        this.editBook = this.editBook.bind(this)
        this.saveBook = this.saveBook.bind(this)
        this.deleteBook = this.deleteBook.bind(this)
        this.logout = this.logout.bind(this)
        this.cancel = this.cancel.bind(this)
        this.reloadPage = this.reloadPage.bind(this)
        this.onChangeCaption = this.onChangeCaption.bind(this)
        this.onChangeGenres = this.onChangeGenres.bind(this)
        this.onChangeAuthors = this.onChangeAuthors.bind(this)
    }

    async reloadPage(_token) {
        return this.checkAuth(_token)
            .then(() => {
                    if (this.state.user.authenticated === true) {
                        this.loadBooks()
                    }
                }
            )
    }

    async componentDidMount() {
        await this.reloadPage(this.state.user.token)
    }

    async checkAuth(_token) {
        return Api.getAuth(_token).then(auth => {
            this.setState({user: auth})
        })
    }

    async loadBooks() {
        return Api.getBooks(this.state.user.token)
            .then(books => {
                this.setState({books: books})
            })
    }

    async deleteBook(_id) {
        return Api.deleteBook(this.state.user.token, _id)
            .then(() => {
                this.loadBooks()
            })
    }

    async editBook(_id) {
        Api.getBook(this.state.user.token, _id)
            .then(result => {
                let authors = ""
                result.authors.forEach(a => authors += a.name + ", ")
                authors = authors.slice(0, authors.length - 2)
                let genres = ""
                result.genres.forEach(a => genres += a.name + ", ")
                genres = genres.slice(0, genres.length - 2)
                result.authors = authors
                result.genres = genres
                this.setState({
                    edit: result
                })
            })
    }

    async saveBook() {
        let caption = document.getElementById("book_caption").value
        let authors = document.getElementById("book_authors").value
        if (authors === "") authors = null
        let genres = document.getElementById("book_genres").value
        if (genres === "") authors = null
        let id = document.getElementById("book_id").value
        let book = {"caption": caption, "authors": authors, "genres": genres, "id": id}
        let method = 'POST'
        if (id != null) {
            method = 'PATCH'
        }
        Api.saveBook(this.state.user.token, book, method).then(r => {
            this.setState({edit:{id:null}})
            this.loadBooks()
        })
    }

    async cancel() {
        this.setState({edit: {id: null}})
        this.loadBooks()
    }

    async login() {
        this.setState({error:null})
        document
            .getElementById('loginButton')
            .setAttribute("disabled", "disabled")
        let username = document.getElementById('username').value
        let password = document.getElementById('password').value
        return Api.login(username, password)
            .then(token => {
                document.getElementById('loginButton')
                    .removeAttribute("disabled")
                if(token===null){
                    this.setState({
                        error:"Wrong login or password"
                    })
                } else {
                    this.reloadPage(token)
                }
            })
    }

    async logout() {
        return Api.logout(this.state.user.token)
            .then(result => {
                if (result === true) {
                    this.checkAuth(this.state.user.token)
                }
            })
    }

    onChangeCaption(event) {
        this.setState({
            edit:{
                caption: event.target.value
            }
        });
    }
    onChangeAuthors(event) {
        this.setState({
            edit:{
                authors: event.target.value
            }
        });
    }
    onChangeGenres(event) {
        this.setState({
            edit:{
                genres: event.target.value
            }
        });
    }

    render() {
        if (this.state.user.authenticated === false) {
            return (
                <div>
                    {this.state.error?<div>{this.state.error}</div>:<div></div>}
                    <input type="text" id="username" placeholder={"Username (admin/user)"}/>
                    <input type="password" id={"password"} placeholder={"Password (admin/pass)"}/>
                    <button id={"loginButton"} onClick={this.login}>Login</button>
                </div>
            )
        } else if (this.state.edit.id !== null) {
            if(this.state.edit.id===-1){
                return (
                    <BookEntity
                        id=""
                        caption=""
                        authors=""
                        genres=""
                        handlerSaveBook={this.saveBook}
                        handlerCancel={this.cancel}
                        handlerChangeCaption={this.onChangeCaption}
                        handlerChangeAuthors={this.onChangeAuthors}
                        handlerChangeGenres={this.onChangeGenres}
                    />
                )
            }
            return (
                <BookEntity
                    id={this.state.edit.id}
                    caption={this.state.edit.caption}
                    authors={this.state.edit.authors}
                    genres={this.state.edit.genres}
                    handlerSaveBook={this.saveBook}
                    handlerCancel={this.cancel}
                    handlerChangeCaption={this.onChangeCaption}
                    handlerChangeAuthors={this.onChangeAuthors}
                    handlerChangeGenres={this.onChangeGenres}
                />
            )
        } else {
            if (this.state.refresh === true) {
                this.setState({refresh: false})
                this.reloadPage(this.state.user.token)
            }
            return (
                <div>
                    <div>You are logged in like {this.state.user.username}.
                        <button onClick={this.logout}>Log out</button>
                    </div>
                    <button onClick={()=>{this.setState({edit:{id:-1}})}}>New book</button>
                    <div>
                        {this.state.books.map(book => {
                            return (
                                <BookItem key={book.id} id={book.id}
                                          caption={book.caption}
                                          authors={book.authors}
                                          genres={book.genres}
                                          handlerDelete={this.deleteBook}
                                          handlerEdit={this.editBook}
                                />

                            )
                        })}
                    </div>
                </div>
            );
        }
    }
}

export default App;
