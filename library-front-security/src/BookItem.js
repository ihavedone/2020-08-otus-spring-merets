import React from 'react'
import Author from "./Author";
import Genre from "./Genre";

const BookItem = (props) => {
    return (
        <div className="bookBlock">
            <div className="bookCaption">{props.caption}</div>
            <div>Authors: {props.authors.map((author, index) => <Author key={"author_"+props.id+"_"+index} id={"author_"+props.id+"_"+index}
                                                                        name={author.name}
            />)}</div>
            <div>Genres: {props.genres.map((genre, index) => <Genre key={"genre_"+props.id+"_"+index} id={"genre_"+props.id+"_"+index}
                                                                    name={genre.name}
            />)}</div>
            <button onClick={() => props.handlerDelete(props.id)}>Delete</button>
            <button onClick={() => props.handlerEdit(props.id)}>Edit</button>
        </div>
    );
}

export default BookItem;
