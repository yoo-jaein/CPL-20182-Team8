var express = require('express');
var router = express.Router();
var PostScript = require('../models/postScript');


router.get('/feed/:feed_id', function(req,res, next){
    PostScript.find({feed_id: req.params.feed_id})
        .then(function(postScript){
            if(!postScript.length) return res.status(404).send({err :  'not found'});
            res.send({success : 1, size : postScript.length, data : postScript});
        }).catch(function(err){
            res.status(500).send(err);
    });
});

router.post('/', function(req, res){
    PostScript.create(req.body)
        .then(function(postScript){
            res.send(postScript);
        })
        .catch(function(err){
            res.status(500).send(err);
        });
});


router.delete('/:postScript_id', function(req,res){
    PostScript.remove({_id : req.params.postScript_id})
        .then(function () {
            res.sendStatus(200);
        }).catch(function (err) {
            res.status(500).send(err);
    });
});


module.exports = router;
