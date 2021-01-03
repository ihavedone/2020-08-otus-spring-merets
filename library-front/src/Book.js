import React from 'react'
import Author from "./Author";
import Genre from "./Genre";

class Book extends React.Component {

    render() {
        return (
            <div>
                <div className="bookCaption">{this.props.caption}</div>
                <div>Authors: {this.props.authors.map((author, index) => <Author key={index} id={index}
                                                                                 name={author.name}
                />)}</div>
                <div>Genres: {this.props.genres.map((genre, index) => <Genre key={index} id={index}
                                                                             name={genre.name}
                />)}</div>
            </div>
        );
    }

}

export default Book;
