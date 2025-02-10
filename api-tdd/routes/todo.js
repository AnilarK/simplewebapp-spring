var express = require('express');
var router = express.Router();

const todos=[{id: 1, name:'eat samosa' , completed: false}]

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.json(todos);
});


router.get('/:id', function(req, res, next) {
    let foundtodo,s=0;
    let n=todos.length;
    for(let i=0;i<n;i++){
        if( todos[i].id===Number(req.params.id) ){
            foundtodo=todos[i]; 
            s=1;
        }
    }

    if(s===0){res.status(404);}
    res.json(foundtodo);
    
  });

router.post('/',function(req,res,next){
    const {body}=req;

    if( typeof body.name !=='string' ){
        res.status(422).text("samosa");}

    const newtodo={
        id:todos.length+1,
        name:body.name,
        completed:false};
    todos.push(newtodo);
    res.status(201).json(newtodo);
})

module.exports = router;
