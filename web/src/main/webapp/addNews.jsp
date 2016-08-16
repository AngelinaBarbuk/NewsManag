<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Title</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link href="http://bootstrap-3.ru/examples/dashboard/dashboard.css" rel="stylesheet">



    <style>
        .input-file-row-1:after {
            content: ".";
            display: block;
            clear: both;
            visibility: hidden;
            line-height: 0;
            height: 0;
        }

        .input-file-row-1{
            display: inline-block;
            margin-top: 25px;
            position: relative;
        }

        html[xmlns] .input-file-row-1{
            display: block;
        }

        * html .input-file-row-1 {
            height: 1%;
        }

        .upload-file-container {
            position: relative;
            width: 100px;
            height: 137px;
            overflow: hidden;
            background: url(http://i.imgur.com/AeUEdJb.png) top center no-repeat;
            float: left;
            margin-left: 23px;
        }

        .upload-file-container:first-child {
            margin-left: 0;
        }

        .upload-file-container > img {
            width: 93px;
            height: 93px;
            border-radius: 5px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
        }

        .upload-file-container-text{
            font-family: Arial, sans-serif;
            font-size: 12px;
            color: #719d2b;
            line-height: 17px;
            text-align: center;
            display: block;
            position: absolute;
            left: 0;
            bottom: 0;
            width: 100px;
            height: 35px;
        }

        .upload-file-container-text > span{
            border-bottom: 1px solid #719d2b;
            cursor: pointer;
        }

        .upload-file-container input  {
            position: absolute;
            left: 0;
            bottom: 0;
            font-size: 1px;
            opacity: 0;
            filter: alpha(opacity=0);
            margin: 0;
            padding: 0;
            border: none;
            width: 70px;
            height: 50px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<%@include file="header.html"%>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="sidebar-brand"> <h3>News Menu</h3></li>
                <li><a href="/web/controller?command=GetNewsList">News List</a></li>
                <li class="active"><a href="#">Add News</a></li>
            </ul>
        </div>
        <div class="col-sm-8 col-md-offset-2 main">
            <h2 class="sub-header">Add News</h2>
            <div class="container-fluid" >
                <form method="post" action="/web/controller"  role="form" enctype="multipart/form-data" class="feedback-form-1">
                    <div class="col-sm-4">
                        <fieldset>
                            <div class="input-file-row-1">

                                <div class="upload-file-container">
                                    <img id="loadedImg" alt="" />
                                    <div class="upload-file-container-text">
                                        <span>Add<br />photo</span>
                                        <input type="file" name="image" class="photo" id="image" />
                                    </div>
                                </div>

                            </div>
                        </fieldset>



                    </div>

                    <div class="col-sm-6">

                        <div class="form-group row">
                            <label for="author">Author: </label>
                            <input type="text" class="form-control" id="author" name="author" placeholder="Enter author" required/>
                        </div>
                        <div class="form-group row">
                            <label for="date">Date:</label>
                            <input type="date" class="form-control" id="date" name="date"/>
                        </div>
                        <div class="form-group row">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" id="title" name="title" placeholder="Titel"/>
                        </div>
                        <div class="form-group row">
                            <label for="content">Content:</label>
                            <textarea class="form-control" id="content" name="content" placeholder="Content" rows="5"></textarea>
                        </div>
                        <input type="hidden" name="command" value="AddNews">
                        <input type="submit" class="btn btn-primary" value="Save"/>
                        <a href="newsList.jsp" class="btn btn-default">Cancle</a>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

<script>
    function readURL(input) {

        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#loadedImg').attr('src', e.target.result);
                //document.getElementById('#loadedImg').src=e.target.result;
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $('#image').change(function(){
        readURL(this);
    });
</script>
</body>
</html>
