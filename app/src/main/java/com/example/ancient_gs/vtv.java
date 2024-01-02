package com.example.ancient_gs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class vtv extends androidx.appcompat.widget.AppCompatTextView {

    public vtv(Context context) {
        super(context);
    }

    public vtv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 保存画布状态
        canvas.save();

        // 将坐标系移动到文本的右上角
        canvas.translate(getWidth(), 0);

        // 旋转画布90度
        canvas.rotate(90);

        // 重新设置文本的颜色、大小等属性
        getPaint().setColor(getCurrentTextColor());
        getPaint().setTextSize(getTextSize());

        // 逐行绘制文字
        String text = getText().toString();
        Paint.FontMetrics fm = getPaint().getFontMetrics();
        float lineHeight = fm.bottom - fm.top;
        float x = 0;
        float y = 0;

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            // 换行符，调整绘制位置
            if (character == '\n') {
                x -= lineHeight;
                y = 0;
            } else {
                // 绘制字符
                canvas.drawText(String.valueOf(character), x, y, getPaint());
                y += getPaint().measureText(String.valueOf(character));
            }
        }

        // 恢复画布状态
        canvas.restore();
    }
}
