import './App.css';
import React from 'react'
import BookList from "./BookList";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import BookForm from "./BookForm";

const Application = () => {
        return (
            <Router>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">Library</Link>
                        </li>
                        <li>
                            <Link to="/new">Add book</Link>
                        </li>
                    </ul>
                </nav>
                <Switch>
                    <Route exact path="/">
                        <BookList/>
                    </Route>
                    <Route exact path="/new">
                        <BookForm/>
                    </Route>
                    <Route exact path="/edit/:id" component={BookForm}>
                    </Route>
                </Switch>
            </Router>
        );
}

export default Application;
