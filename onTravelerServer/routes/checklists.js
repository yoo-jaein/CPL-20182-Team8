var express = require('express');
var router = express.Router();

/* GET checklist listing. */
router.get('/', function(req, res, next) {
    res.send('respond with a resource');
});


/* Get particular checklist*/
router.get('/:checklist_id', function(req, res, next){
    res.end();
});


/* Get particular checklist list */
router.get('/customer/:customer_id', function(req, res, next){
    res.end();
});


/* Get particular checklist list */
router.get('/buddy/:buddy_id', function(req, res, next){
    res.end();
});


/* Create checklist or update */
router.post('/', function(req, res){
    res.end();
});


/* Delete checklist */
router.delete('/:checklist_id', function(req, res){
    res.end();
});

module.exports = router;
