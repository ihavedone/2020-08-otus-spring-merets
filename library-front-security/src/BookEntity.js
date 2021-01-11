const BookEntity = (props) => {
    return (
        <div className="grid">
            <input type={"hidden"} id={"book_id"} onChange={()=>{}} value={props.id}/>
            <div className="row">
                <span className="cell3">Caption:</span>
                <span className="cell7"><input type={"text"} id={"book_caption"} onChange={(event)=>props.handlerChangeCaption(event)} value={props.caption}/></span>
            </div>
            <div className="row">
                <span className="cell3">Authors:</span>
                <span className="cell7"><input type={"text"} id={"book_authors"} onChange={(event)=>props.handlerChangeAuthors(event)} value={props.authors}/></span>
            </div>
            <div className="row">
                <span className="cell3">Genres:</span>
                <span className="cell7"><input type={"text"} id={"book_genres"} onChange={(event)=>props.handlerChangeGenres(event)} value={props.genres}/></span>
            </div>
            <button onClick={() => {
                props.handlerSaveBook()
            }}>Save
            </button>
            <button onClick={() => {
                props.handlerCancel()
            }}>Cancel
            </button>
        </div>
    )
}

export default BookEntity
