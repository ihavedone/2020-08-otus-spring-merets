import React from 'react'

class Author extends React.Component{
    render(){
        return (
            <span className="authorBlock">{this.props.name}</span>
        );
    }
}

export default Author;
