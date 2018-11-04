<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Customers</title>
</head>
<body>
    <section>
        <div class="jumbotron">
            <div class="container">
                <h1>Customers</h1>
                <p>All our Customers</p>
            </div>
        </div>
    </section>
    <section class="container">
        <div class="row">
            <c:forEach items="${requestScope.customers}" var="customers">
                <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                    <div class="thumbnail">
                        <div class="caption">
                            <h3>${customers.name}</h3>
                            <p>${customers.address}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</body>
</html>
