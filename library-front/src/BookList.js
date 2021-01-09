import React from 'react'
import Book from './Book'
import API from './ApiService'

class BookList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            books: []
        }
        this.deleteBook = this.deleteBook.bind(this);
    }

    async componentDidMount() {
        let books = await API.getBooks()
        this.setState({
            books: books
        })
    }

    async deleteBook(id) {
        let deleteFlag = await API.deleteBook(id)
        if (deleteFlag) {
            let books = await API.getBooks()
            this.setState({
                    books: books
                }
            )
        }
    }

    render() {
        return (<div>
            {this.state.books.map((book, index) => {
                return (
                    <Book key={book.id} id={book.id}
                          caption={book.caption}
                          authors={book.authors}
                          genres={book.genres}
                          handlerDelete={this.deleteBook}
                    />)
            })}
        </div>)
    }
}

export default BookList;
