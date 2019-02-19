<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section id="container">
	<h2 class="invisible">본문 시작</h2>
	
	<style type="text/css">
		.wrap_video{display:none;   position:fixed;top:0;bottom:0;left:50%;z-index:1;-webkit-transform:translateX(-50%);-moz-transform:translateX(-50%);transform:translateX(-50%)}
		.wrap_video video {height:100%;-webkit-transform:scale(1.5);-moz-transform:scale(1.5);transform:scale(1.5);-webkit-filter:blur(11px);filter:blur(5px)}
	</style>
	<div class="wrap_video">
		<video autoplay loop muted>
			<source src="/admin/video/Sun-15138.mp4" type="video/mp4" />
			<source src="/admin/video/Sun-15138.ogg" type="video/ogg" />
		</video>
	</div><!-- // wrap_video -->