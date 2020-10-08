<%--
  Created by IntelliJ IDEA.
  User: weilanyu
  Date: 10/7/20
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weilan's Mailbox</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
</head>
<body>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
    <a class="navbar-brand" href="#">
        <svg t="1602106561872" class="icon" viewBox="0 0 1046 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3310" width="64" height="64">
            <path d="M842.624 43.3152m-43.3152 0a43.3152 43.3152 0 1 0 86.6304 0 43.3152 43.3152 0 1 0-86.6304 0Z" fill="#3282FA" p-id="3311"></path>
            <path d="M198.8864 173.2864m-43.3152 0a43.3152 43.3152 0 1 0 86.6304 0 43.3152 43.3152 0 1 0-86.6304 0Z" fill="#3282FA" p-id="3312"></path>
            <path d="M357.7344 57.7536m-43.3152 0a43.3152 43.3152 0 1 0 86.6304 0 43.3152 43.3152 0 1 0-86.6304 0Z" fill="#66FF00" p-id="3313"></path>
            <path d="M987.7248 556.9792a25.6 25.6 0 0 0 0.64-36.1984L668.2624 189.312a25.6 25.6 0 0 0-36.1984-0.64L103.168 699.4176a25.6 25.6 0 0 0-0.64 36.1728l201.6256 209.408 276.7872 4.8384 406.784-392.8576zM67.6096 662.6048L596.48 151.8592a76.8 76.8 0 0 1 108.5952 1.8944l320.1024 331.4688a76.8 76.8 0 0 1-1.8944 108.5952l-422.0672 407.552-319.232-5.5552-216.3712-224.6912a76.8 76.8 0 0 1 1.9712-108.544z" fill="#3282FA" p-id="3314"></path>
            <path d="M610.048 697.3952a25.6 25.6 0 0 0 18.8928-21.504l51.0464-398.2592a25.6 25.6 0 1 0-50.7904-6.5024l-48.8192 380.928-384.7168 56.1664a25.6 25.6 0 0 0 7.424 50.688l397.2608-58.0352a25.5232 25.5232 0 0 0 9.7024-3.4816z" fill="#3282FA" p-id="3315"></path>
            <path d="M51.2 1010.944h959.6672a25.6 25.6 0 0 0 25.6-25.6v-20.992a25.6 25.6 0 0 0-25.6-25.6H51.2a25.6 25.6 0 0 0-25.6 25.6v20.992a25.6 25.6 0 0 0 25.6 25.6z" fill="#66FF00" p-id="3316"></path>
        </svg>
        Weilan's Mailbox
    </a>
</nav>
<div role="main" class="jumbotron bg-light mb-0">
    <div class="container mt-2 align-self-center">
        <div class="row">
            <div class="col col-md-6 offset-md-3">
                <form id="emailForm" action="sendMail" method="post">
                    <div class="form-group">
                        <label for="recipients">To:</label>
                        <input type="text" data-bv-emailaddress-multiple="true" data-bv-emailaddress="true" class="form-control" id="recipients" placeholder="Enter Recipient email" name="recipients">
                        <small id="emailHelp" class="form-text text-muted">Separate multiple recipients by comma or semicolon </small>
                    </div>
                    <div class="form-group">
                        <label for="subject">Subject:</label>
                        <input type="text" class="form-control" id="subject" placeholder="Subject" name="subject">
                    </div>
                    <div class="form-group">
                        <label for="sender">From:</label>
                        <input readonly type="email" class="form-control" id="sender" aria-describedby="emailHelp" name="sender" value="mymail@weilanyu.io">
                    </div>
                    <div class="form-group">
                        <label for="content">Body:</label>
                        <textarea class="form-control" id="content" rows="5" name="content"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Send</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        // validate email address
        $('#emailForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                recipients: {
                    validators: {
                        notEmpty: {
                            message: 'Email is required and cannot be empty'
                        },
                        emailAddress: {
                            message: 'The value is not a valid email address'
                        }
                    }
                },
                subject: {
                    validators: {
                        notEmpty: {
                            message: 'The subject cannot be empty'
                        }
                    }
                },
                content: {
                    validators: {
                        notEmpty: {
                            message: 'The email body cannot be empty'
                        }
                    }
                }
            }
        });
    });
</script>
</body>
</html>
