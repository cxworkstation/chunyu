<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 上面有java编译命令，所以能进行尖括号百分号的运算 -->
<%
    String str=request.getAttribute("str").toString();
 %>
 
 <!-- 在js中其实不能拿到尖括号百分号里面的变量，因为它们属于不同的域，一个是java代码，一个是js代码，完全不同 
 但是在这个页面中都能用到尖括号百分号,也就是说能解析尖括号百分号。
 
 因为js是非编译性语言，而是解释性语言，说的更形象一点，js和HTML都一样，它们都是字符串，然后被解释执行。这里其实js根本
 拿不到这个尖括号百分号里面的变量，只是这个尖括号百分号能得到解析，从而像拼接字符串一样拼接起来的js语句。
  -->
<script>var test='<%=str%>'</script>