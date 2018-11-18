var express = require('express');
var router = express.Router();
var PostScript = require('../models/postScript');
var ReturnFormat = require('../return_form')();


router.get('/', function(req,res, next){
    PostScript.findAll()
        .then(function(postScript){
            if(!postScript.length) return res.status(404).send(ReturnFormat.get_format(0,postScript,"cannot found"));
            res.send(ReturnFormat.get_format(1,postScript,""));
        }).catch(function(err){
        res.status(500).send(ReturnFormat.get_format(0, "", err));
    });
});


router.get('/feed/:feed_id', function(req,res, next){
    PostScript.find({feed_id: req.params.feed_id})
        .then(function(postScript){
            if(!postScript.length) return res.status(404).send(ReturnFormat.get_format(0,postScript,"cannot found"));
            res.send(ReturnFormat.get_format(1,postScript,""));
        }).catch(function(err){
            res.status(500).send(ReturnFormat.get_format(0, "", err));
    });
});

router.post('/', function(req, res){
    PostScript.create(req.body)
        .then(function(postScript){
            res.send(ReturnFormat.post_format(1, [postScript], ""));
        })
        .catch(function(err){
            res.status(500).send(ReturnFormat.post_format(0, "", err));
        });
});


router.delete('/:postScript_id', function(req,res){
    PostScript.remove({_id : req.params.postScript_id})
        .then(function () {
            res.send(ReturnFormat.delete_format(1, ""));
        }).catch(function (err) {
            res.status(500).send(ReturnFormat.delete_format(0, err));
    });
});


module.exports = router;
