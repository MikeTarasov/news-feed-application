//Show putting news form
function put_news(id) {
    $('#put-news-form').css('display', 'flex');
    $.ajax({
        method: "GET",
        url: '/news/' + id,
        success: function (response) {
            document.getElementById('news-id').setAttribute('value', id);
            document.getElementById('news-name').setAttribute('value', response.name);
            document.getElementById('news-text').setAttribute('value', response.text);
            document.getElementById('category').setAttribute('value', response.category);
        },
        error: function (response) {
            if (response.status === 404) {
                alert('Новость не найдена!');
                location.reload();
            }
        }
    });
    return false;
}

//Delete news
function delete_news(id) {
    const link = $(this);
    $.ajax({
        method: "DELETE",
        url: '/news/' + id,
        success: function (response) {
            this.url.delete();
        },
        error: function (response) {
            if (response.status === 404) {
                alert('Новость не найдена!');
            }
        }
    });
    setTimeout(function () {
        location.reload();
    }, 100);
    return false;
}

$(function () {
    //Every 2 clicks on .news_link reload page
    let count = 0;

    //Show adding news form
    $('#show-add-news-form').click(function () {
        $('#add-news-form').css('display', 'flex');
    });

    //Closing adding news form
    $('#add-news-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none')
        }
    });

    //Closing putting news form
    $('#put-news-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none')
        }
    });

    //Getting News by id
    $(document).on('click', '.news-link', function () {
        const link = $(this);
        const newsId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/news/' + newsId,
            success: function (response) {
                const code = '<br>Категория: ' + response.category + '<br>Дата: ' + response.date
                    + '<br>Текст новости: ' + response.text + '<br>';
                if (count === 0) {
                    link.parent().append(code);
                    count++;
                } else {
                    location.reload();
                    count--;
                }
            },
            error: function (response) {
                if (response.status === 404) {
                    alert('Новость не найдена!');
                    location.reload();
                }
            }
        });
        return false;
    });

    //Getting News by category id
    $(document).on('click', '.category-news-link', function () {
        const link = $(this);
        const categoryId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/category/' + categoryId,
            success: function (response) {
                location.reload();
                window.document.write(response);
            },
            error: function (response) {
                if (response.status === 404) {
                    alert('Новость не найдена!');
                    location.reload();
                }
            }
        });
        return false;
    });

    //Search news by pattern
    $('#search-button').click(function () {
        const text = document.getElementById("text").value;
        const data = {'text': text};
        $.ajax({
            method: "GET",
            url: '/news/search',
            data: data,
            success: function (response) {
                location.reload();
                window.document.write(response)
            },
            error: function (response) {
                if (response.status === 404) {
                    alert('Страница не найдена');
                    location.reload();
                }
            }
        });
        return false;
    });

    //Adding news
    $('#save-news').click(function () {
        const data = $('#add-news-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/news/add',
            data: data,
            context: this,
            success: function (response) {
                $('#add-news-form').css('display', 'none');
                location.reload();
            }
        });
        $(this)[0].reset;
        setTimeout(function () {
            location.reload();
        }, 100);
        return false;
    });

    //Edit news
    $('#put-news-save').click(function () {
        const data = $('#put-news-form form').serialize();
        const id = document.getElementById('news-id').value;
        $.ajax({
            method: "PUT",
            url: '/news/' + id,
            data: data,
            context: this,
            success: function (response) {
                $('#put-news-form').css('display', 'none');
                location.reload();
            }
        });
        $(this)[0].reset;
        setTimeout(function () {
            location.reload();
        }, 100);
        return false;
    });
});