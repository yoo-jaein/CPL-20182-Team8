var express = require('express');
var router = express.Router();
var Checklist = require('../models/checklist');

/* GET checklist listing. */
router.get('/', function(req, res, next) {
    Checklist.findAll()
        .then(function(checklist){
            if(!checklist.length) return res.status(404).send({err : 'not found'});
            res.send({success:1, size : checklist.length, data : checklist});
        }).catch(function(err){
            res.status(500).send(err);
    });
});


/* Get particular checklist*/
router.get('/:checklist_id', function(req, res, next){
    Checklist.find({_id : req.params.checklist_id})
        .then(function(checklist){
            if(!checklist.length) return res.status(404).send({err : 'not found'});
            res.send({success:1, size:checklist.length, data : checklist});
        }).catch(function(err){
            res.status(500).send(err);
    });
});


/* Get particular checklist list */
router.get('/customer/:customer_id', function(req, res, next){
    Checklist.find({customer_id : req.params.customer_id})
        .then(function(checklist){
            if(!checklist.length) return res.status(404).send({err : 'not found'});
            res.send({success:1, size:checklist.length, data : checklist});
        }).catch(function(err){
        res.status(500).send(err);
    });
});


/* Get particular checklist list */
router.get('/buddy/:buddy_id', function(req, res, next){
    Checklist.find({buddy_id : req.params.buddy_id})
        .then(function(checklist){
            if(!checklist.length) return res.status(404).send({err : 'not found'});
            res.send({success:1, size:checklist.length, data : checklist});
        }).catch(function(err){
        res.status(500).send(err);
    });
});


/* Create checklist or update */
router.post('/', function(req, res){
    Checklist.create(req.body)
        .then(function(checklist){
            res.send(checklist);
        }).catch(function(err){
            res.status(500).send(err);
    });
});


/* Delete checklist */
router.delete('/:checklist_id', function(req, res){
    Checklist.remove({_id : req.params.checklist_id})
        .then(function(){
            res.sendStatus(200);
        }).catch(function(err){
            res.status(500).send(err);
    });
});

module.exports = router;
