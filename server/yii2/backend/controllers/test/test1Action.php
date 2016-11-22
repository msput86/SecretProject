<?php

namespace app\controllers\test;

use Yii;
use yii\base\Action;
use yii\web\Response;

class Test1Action extends Action {
    public function run($param=null) {
        Yii::$app->response->format = Response::FORMAT_JSON;

        if (!$param) {
            $res = 'You say "NULL"';
        } else {
            $res = 'You say "'.$param.'"';
        }

        return $res;
    }
}