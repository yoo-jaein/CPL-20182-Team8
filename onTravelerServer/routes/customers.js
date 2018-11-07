var express = require('express');
var router = express.Router();
var Customer = require('../models/customer');

/* GET customer listing. */
router.get('/', function(req, res, next) {
    Customer.findAll()
        .then(function(customer){
            if(!customer.length) return res.status(404).send({err : 'not found'});
            res.send({success:1, size:customer.length, data : customer});
        })
        .catch(function(err){
            res.status(500).send(err);
        });
});


/* Get particular customer*/
router.get('/:customer_id', function(req, res, next){
    Customer.find({customer_id : req.params.customer_id})
        .then(function(customer){
            if(!customer.length) return res.status(404).send({err : 'not found'});
            res.send({success:1, size:customer.length, data : customer});
        })
        .catch(function(err){
            res.status(500).send(err);
        });
});


/* Create customer or update */
router.post('/', function(req, res){
    Customer.create(req.body)
        .then(function(customer){
            res.send(customer);
        }).catch(function(err){
            res.status(500).send(err);
    });
});


/* Delete customer */
router.delete('/:customer_id', function(req, res){
    Customer.remove({customer_id: req.params.customer_id})
        .then(function(){
            res.sendStatus(200);
        }).catch(function(err){
            res.status(500).send(err);
    });
});

module.exports = router;
