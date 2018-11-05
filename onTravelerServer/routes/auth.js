
var express = require('express');
var router = express.Router();
var Buddy = require('../models/buddy');
var Customer = require('../models/customer');

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
                if(!buddy.length){
                    Buddy.create({
                        buddy_id : req.user.id,
                        pw : "0",
                        name : req.user.name.familyName + req.user.name.givenName
                    });

                    res.send({status : 'created account!'});
                }

                /*  있으면 */
                res.send({success:1, size:buddy.length, data:buddy});
            }).catch(function (err) {
            res.status(500).send(err);
        });
    });

module.exports = router;
