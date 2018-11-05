var express = require('express');
var router = express.Router();
var ScheduleItem = require('../models/schedule');

/* GET schedule_item listing. */
router.get('/', function(req, res, next) {
    res.send('respond with a resource');
});


/* Get particular schedule_item*/
router.get('/:schedule_item_id', function(req, res, next){
    ScheduleItem.find({_id : req.params.schedule_item_id})
        .then(function(schedule_item){
            if(!schedule_item.length) return res.status(404).send({err : 'not found'});
            res.send({success:1, size:schedule_item.length, data : schedule_item});
        }).catch(function (err) {
            res.send(500).send(err);
    });
});


router.get('/buddy/:buddy_id', function(req, res, next){
    ScheduleItem.find({buddy_id : req.params.buddy_id})
        .then(function(schedule_item){
            if(!schedule_item.length) return res.status(404).send({err : 'not found'});
            res.send({success:1, size:schedule_item.length, data : schedule_item});
        }).catch(function (err) {
        res.send(500).send(err);
    });
});


/* Create schedule_item or update */
router.post('/', function(req, res){
    ScheduleItem.create(req.body)
        .then(function(schedule_item){
            res.send(schedule_item);
        }).catch(function(err){
            res.send(500).status(err);
    });
});


/* Delete schedule_item */
router.delete('/:schedule_item_id', function(req, res){
    ScheduleItem.remove({_id : req.params.schedule_item_id})
        .then(function () {
            res.sendStatus(200);
        }).catch(function (err) {
            res.status(500).send(err);
    });
});

module.exports = router;
