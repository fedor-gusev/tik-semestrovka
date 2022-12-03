<html>

<head>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <title>
        Хаффман - загрузка таблицы
    </title>

</head>

<body>

<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">&nbsp;&nbsp;&nbsp;Алгоритм Хаффмана - загрузка таблицы</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="/">На главную</a></li>
            <li><a href="/huffman">Алгоритм Хаффмана</a></li>
            <li><a href="https://vk.com/httprequest">Заглянуть к автору</a></li>
        </ul>
    </div>
</nav>

<div class="container">

<h3>Пояснение</h3>
Здесь вы можете загрузить файл с символами алфавита и их вероятностями. Для этого входной файл должен
состоять из двух строк и иметь следующий формат:
<br/>
Символ1 Символ2 Символ3 и тд<br/>
Вероят1 Вероят2 Вероят3 и тд

<form method="post" enctype="multipart/form-data" action="/huffman/cp">
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