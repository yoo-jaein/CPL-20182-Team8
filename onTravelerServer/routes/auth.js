
var express = require('express');
var router = express.Router();
var Buddy = require('../models/buddy');
var Customer = require('../models/customer');
var ReturnFormat = require('../return_form')();

const passport = require('passport');

/* GET home page. */
router.get('/google', passport.authenticate('google',
    { scope:
        ['profile']}
        )
);

router.get('/google/callback', passport.authenticate('google', { failureRedirect: '/auth'}),
    function (req, res) {

        /*
        * 없으면 회원가입
        * */
        Buddy.find({buddy_id : req.user.id})
            .then(function(buddy){
                console.log(buddy.length);
                if(buddy.length){
                    console.log('exist');
                    res.send(ReturnFormat.auth_format(1, 0, buddy, ""));
                }else if(!buddy.length) {
                    Buddy.create({
                        buddy_id: req.user.id,
                        pw: "0",
                        name: req.user.name.familyName + req.user.name.givenName
                    }).then(function(buddy){
                        res.send(ReturnFormat.auth_format(1, 1, buddy, ""));
                    });
                }
            }).catch(function (err) {
            res.status(500).send(ReturnFormat.auth_format(0, 0, "", err));
        });
    });

module.exports = router;
