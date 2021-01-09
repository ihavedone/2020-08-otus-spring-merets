import React from 'react'
import Author from "./Author";
import Genre from "./Genre";
import {Link} from "react-router-dom";

const Book = (props) => {
    return (
        <div className="bookBlock">
            <div className="bookCaption">{props.caption}</div>
            <div>Authors: {props.authors.map((author, index) => <Author key={index} id={index}
                                                                        name={author.name}
            />)}</div>
            <div>Genres: {props.genres.map((genre, index) => <Genre key={index} id={index}
                                                                    name={genre.name}
            />)}</div>
            <button onClick={() => props.handlerDelete(props.id)}>Delete</button>
            <Link to={"/edit/" + props.id}>
                <button>Edit</button>
            </Link>
        </div>
    );
}

export default Book;
