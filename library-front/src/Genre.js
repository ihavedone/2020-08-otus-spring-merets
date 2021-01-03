import React from 'react'

class Genre extends React.Component{
    render(){
        return (
            <span className="genreBlock">{this.props.name}</span>
        );
    }
}

export default Genre;
