import React from 'react'
import Book from './Book'
import {Link} from 'react-router-dom'

class BookList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            books: []
        }
        this.getServerData = this.getServerData.bind(this);
        this.deleteBook = this.deleteBook.bind(this);
    }

    componentDidMount() {
        this.getServerData()
    }

    deleteBook(id) {
        fetch('/api/book/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((result) => {
            this.getServerData()
        })
    }

    getServerData() {
        fetch('/api/book/')
            .then(result => result.json())
            .then((result) => {
                this.setState({
                    books: result
                })
            })
    }

    render() {
        return (
            <div>
                {this.state.books.map((book, index) => {
                    return <div className="bookBlock">
                        <Book key={book.id} id={book.id}
                              caption={book.caption}
                              authors={book.authors}
                              genres={book.genres}
                        />
                        <button onClick={() => this.deleteBook(book.id)}>Delete</button>
                        <Link to={"/edit/" + book.id}>
                            <button>Edit</button>
                        </Link>
                    </div>
                })}
            </div>
        );
    }

}

export default BookList;
