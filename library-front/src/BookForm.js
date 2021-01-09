import React from 'react'
import {Redirect} from 'react-router-dom'
import API from './ApiService'

class BookForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            book_caption: "",
            book_id: null,
            book_authors: "",
            book_genres: "",
            redirect: false
        }
        this.handleChangeCaption = this.handleChangeCaption.bind(this);
        this.handleChangeAuthors = this.handleChangeAuthors.bind(this);
        this.handleChangeGenres = this.handleChangeGenres.bind(this);
        this.saveHandler = this.saveHandler.bind(this);
    }

    async componentDidMount() {
        if (typeof (this.props.match) != 'undefined' && this.props.match != null) {
            let id = this.props.match.params.id
            let book = await API.getBook(id)
            this.setState({
                book_caption: book.caption,
                book_id: book.id,
                book_authors: book.authors,
                book_genres: book.genres
            })
        }
    }

    saveHandler() {
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
        API.saveBook(book, method).then(r => {
            this.setState({redirect: true})
        })
    }

    handleChangeCaption(event) {
        this.setState({
            book_caption: event.target.value
        });
    }

    handleChangeAuthors(event) {
        this.setState({
            book_authors: event.target.value
        });
    }

    handleChangeGenres(event) {
        this.setState({
            book_genres: event.target.value
        });
    }

    render() {
        if (this.state.redirect) {
            return <Redirect push to="/"/>;
        }
        return (
            <div className="grid">
                <input type="hidden" id="book_id" onChange={() => {
                }} value={ this.state.book_id!=null?this.state.book_id:""}/>
                <div className="row"><span className="cell3">Caption:</span>
                    <span className="cell7"><input type="text" className="textField" id="book_caption"
                                                   onChange={this.handleChangeCaption}
                                                   placeholder="Caption of the book" value={this.state.book_caption}/>
                    </span>
                </div>
                <div className="row"><span className="cell3">Authors (via comma):</span>
                    <span className="cell7"><input type="text"
                                                   className="textField"
                                                   id="book_authors"
                                                   onChange={this.handleChangeAuthors}
                                                   value={this.state.book_authors}
                                                   placeholder="e.g. Leo Tolstoi, Mark Antony"/>
                    </span>
                </div>
                <div className="row"><span className="cell3">Genres (via comma):</span>
                    <span className="cell7"><input type="text"
                                                   className="textField"
                                                   id="book_genres"
                                                   onChange={this.handleChangeGenres}
                                                   value={this.state.book_genres}
                                                   placeholder="e.g. Comedy, Fantasy"/>
                    </span>
                </div>
                <div className="row">
                    <button onClick={() => this.saveHandler()}>Save</button>
                </div>
            </div>
        );
    }
}

export default BookForm;
