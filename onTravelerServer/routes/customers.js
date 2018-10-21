var express = require('express');
var router = express.Router();

/* GET customer listing. */
router.get('/', function(req, res, next) {
    res.send('respond with a resource');
});


/* Get particular customer*/
router.get('/:customer_id', function(req, res, next){
    res.end();
});


/* Create customer or update */
router.post('/', function(req, res){
    res.end();
});


/* Delete customer */
router.delete('/:customer_id', function(req, res){
    res.end();
});

module.exports = router;
