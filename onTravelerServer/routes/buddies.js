var express = require('express');
var router = express.Router();
var Buddy = require('../models/buddy');

/* GET buddy listing. */
router.get('/', function(req, res, next) {
    Buddy.findAll()
        .then(function(buddy){
            if(!buddy.length) return res.status(404).send({ err : 'not found'});
            res.send({ success : 1, size : buddy.length, data : buddy});
        }
    )
    .catch(function (err) {
        res.status(500).send(err);
    });
});


/* Get particular buddy*/
router.get('/:buddy_id', function(req, res, next){
    Buddy.find({buddy_id : req.params.buddy_id})
        .then(function(buddy){
            if(!buddy.length) return res.status(404).send({ err : 'not found'});
            res.send({ success : 1, size : buddy.length, data : buddy});
        })
        .catch(function(err){
            res.status(500).send(err);
        });
});


/* Get particular buddy list */
router.get('/location/:location_name', function(req, res, next){
    Buddy.find({active_location : req.params.location_name})
        .then(function(buddy){
            if(!buddy.length) return res.status(404).send({ err : 'not found'});
            res.send({ success : 1, size : buddy.length, data : buddy});
        })
        .catch(function(err){
            res.status(500).send(err);
        });
});


/* Create buddy or update */
router.post('/', function(req, res){
    Buddy.create(req.body)
        .then(function(buddy){
            res.send(buddy);
        }).catch(function(err){
            res.status(500).send(err);
    });
});


/* Delete buddy */
router.delete('/:buddy_id', function(req, res){
    Buddy.remove({buddy_id : req.params.buddy_id})
        .then(function(){
            res.sendStatus(200);
        }).catch((function (err) {
            res.status(500).send(err);
    }))
});

module.exports = router;
