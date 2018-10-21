var express = require('express');
var router = express.Router();

/* GET schedule_item listing. */
router.get('/', function(req, res, next) {
    res.send('respond with a resource');
});


/* Get particular schedule_item*/
router.get('/:schedule_item_id', function(req, res, next){
    res.end();
});


/* Create schedule_item or update */
router.post('/', function(req, res){
    res.end();
});


/* Delete schedule_item */
router.delete('/:schedule_item_id', function(req, res){
    res.end();
});

module.exports = router;
