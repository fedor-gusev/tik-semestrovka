<html>

<head>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <title>
        Хаффман - загрузка существующего кода
    </title>

</head>

<body>

<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">&nbsp;&nbsp;&nbsp;Алгоритм Хаффмана - загрузка существующего кода</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="/">На главную</a></li>
            <li><a href="/huffman">Алгоритм Хаффмана</a></li>
            <li><a href="https://vk.com/httprequest">Заглянуть к автору</a></li>
        </ul>
    </div>
</nav>

<div class="container">

<h3>Пояснение</h3>
Здесь вы можете загрузить уже существующие коды. Для этого входной файл должен иметь формат:
<br/>
Символ Код<br/>
Символ Код<br/>
Символ Код<br/><br/>
и так далее.

<form method="post" enctype="multipart/form-data" action="/huffman/code">
    <div class="file-field input-field">
        <div class="btn">
            <span>File</span>
            <input type="file" name="file">
        </div>
        <div class="file-path-wrapper">
            <input class="file-path validate" type="text">
        </div>
    </div>
    <input class="waves-effect waves-light btn" type="submit" value="Загрузить!"/>
</form>

</div>
</body>

</html>