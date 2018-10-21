var express = require('express');
var router = express.Router();

/* GET feed_item listing. */
router.get('/', function(req, res, next) {
    res.send('respond with a resource');
});


/* Get particular feed_item*/
router.get('/:feed_item_id', function(req, res, next){
    res.end();
});


/* Get particular feed_item */
router.get('/hashtag/:hashtag_name', function(req, res, next){

})


/* Create feed_item or update */
router.post('/', function(req, res){
    res.end();
});


/* Delete feed_item */
router.delete('/:feed_item_id', function(req, res){
    res.end();
});

module.exports = router;
