
    const deleteNote = (event,element,id) => {
        event.stopPropagation();
        fetch('moneynotes/'+ id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        }).then(function(response) {
            element.parentNode.parentNode.parentNode.removeChild(element.parentNode.parentNode);
             location.reload();
        })
    }
