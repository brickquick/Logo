package brickonization.logo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Logo extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	Sprite brickImage;
	Sprite logoImage;
	Vector3 touchPos;

	Array<Rectangle> bricks;
	Array<Rectangle> oldBricks;
	Array<Float> layer1;
	Array<Float> layer2;
	Array<Float> layer3;
	Array<Float> layer4;
	Array<Float> layer5;
	Array<Float> layer6;
	Array<Float> layer7;
	Array<Float> layer8;
	Array<Float> layer9;

	long lastBrickTime;
	long increase = 600000000;
	String name = "brickonization";
	char ch;
	int length;
	int indent = 1;
	int v = 0;
	int layer = 1;
	int a = 0;
	int j = 0;
	float width = 30, height = width/2;
	float r = 0, r2 = 0;
	float desiredWidth = 800 * 1.0f;
	boolean canSpawn = true;

	@Override
	public void create () {
		camera = new  OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();

		touchPos = new Vector3();

		brickImage = new Sprite(new Texture("brick.png"));
		logoImage = new Sprite(new Texture("logo.png"));

		bricks = new Array<Rectangle>();
		oldBricks = new Array<Rectangle>();

		layer1 = new Array<Float>();
		layer1.add((float) indent);
		layer2 = new Array<Float>();
		layer2.add((float) indent);
		layer3 = new Array<Float>();
		layer3.add((float) indent);
		layer4 = new Array<Float>();
		layer4.add((float) indent);
		layer5 = new Array<Float>();
		layer5.add((float) indent);
		layer6 = new Array<Float>();
		layer6.add((float) indent);
		layer7 = new Array<Float>();
		layer7.add((float) indent);
		layer8 = new Array<Float>();
		layer8.add((float) indent);
		layer9 = new Array<Float>();
		layer9.add((float) indent);

		name = "brickonization";
		quantity(name);

		spawnBricks();

	}

	private void spawnBricks() {
		Rectangle brick = new Rectangle();

		brick.width = width;
		brick.height = height;
		brick.y = 480;
		//System.out.println(layer8.size + " " + layer9.size + " " + layer);

		//////////////////////////////use correct layer
		if (layer == 1) {
			a = MathUtils.random(0, layer1.size -2 );
			brick.x = layer1.get(a);
			layer1.removeIndex(a);
		}
		if (layer == 2){
			a = MathUtils.random(0, layer2.size -2 );
			brick.x = layer2.get(a);
			layer2.removeIndex(a);
		}
		if (layer == 3){
			a = MathUtils.random(0, layer3.size -2 );
			brick.x = layer3.get(a);
			layer3.removeIndex(a);
		}
		if (layer == 4){
			a = MathUtils.random(0, layer4.size -2 );
			brick.x = layer4.get(a);
			layer4.removeIndex(a);
		}
		if (layer == 5){
			a = MathUtils.random(0, layer5.size -2 );
			brick.x = layer5.get(a);
			layer5.removeIndex(a);
		}
		if (layer == 6){
			a = MathUtils.random(0, layer6.size -2 );
			brick.x = layer6.get(a);
			layer6.removeIndex(a);
		}
		if (layer == 7){
			a = MathUtils.random(0, layer7.size -2 );
			brick.x = layer7.get(a);
			layer7.removeIndex(a);
		}
		if (layer == 8){
			a = MathUtils.random(0, layer8.size -2 );
			brick.x = layer8.get(a);
			layer8.removeIndex(a);
		}
		if (layer == 9){
			a = MathUtils.random(0, layer9.size -2 );
			brick.x = layer9.get(a);
			layer9.removeIndex(a);
		}

		bricks.add(brick);

		/////////////////////////////////test layer
		if(layer1.size==1){
			layer = 2;
		}
		if(layer2.size==1){
			layer = 3;
		}
		if(layer3.size==1){
			layer = 4;
		}
		if(layer4.size==1){
			layer = 5;
		}
		if(layer5.size==1){
			layer = 6;
		}
		if(layer6.size==1){
			layer = 7;
		}
		if(layer7.size==1){
			layer = 8;
		}
		if(layer8.size==1){
			layer = 9;
		}
		if(layer9.size==1){
			layer = 10;
		}

		lastBrickTime = TimeUtils.nanoTime();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);


		//////////////////////////////////////////////////////////////////////touch
		if (Gdx.input.justTouched()){
			canSpawn = false;
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			oldBricks.removeAll(oldBricks, true);
			oldBricks.addAll(bricks);
		}

		if (canSpawn) {
			////////////////////////////////////////draw bricks
			batch.begin();
			for (Rectangle brick: bricks) {
				batch.draw(brickImage, brick.x, brick.y, brick.width, brick.height);
			}
			batch.end();

			///////////////////////spawnd per correct time
			if (layer != 10) {
				if (TimeUtils.nanoTime() - lastBrickTime > increase) {
					//increase -= t*500000; //speed spawn
					spawnBricks();
				}
			}
			///////////////////////////////////////////////////////////////////bricks moove
			j = 0;
			for (Rectangle brick : bricks) {
				j++;
				v = -190;

				for (int i = 0; i < bricks.size; i++) {
					if (j != i + 1) {
						if (brick.overlaps(bricks.get(i))) {
							v = 0;
						}
					}
				}

				if (brick.y <= 480 / 2) {
					brick.y = 480 / 2;
				} else {
					brick.y += v * Gdx.graphics.getDeltaTime();
				}

			}
		}else {
			batch.begin();
			if (bricks.size == 0){ //logo after bricks
				batch.draw(logoImage, 1, 480/2,
						logoImage.getWidth() * (desiredWidth / logoImage.getWidth()),
						logoImage.getHeight() * (desiredWidth / logoImage.getWidth()));
			}else {
				////////////////////////////////////////draw bricks
				for (Rectangle brick : bricks) {
					r += 3.4 * Gdx.graphics.getDeltaTime(); //set rotate bricks
					r2 += -3.4 * Gdx.graphics.getDeltaTime();

					if (brick.x + width / 2 < touchPos.x && brick.y + height / 2 > touchPos.y || brick.x + width / 2 > touchPos.x && brick.y + height / 2 < touchPos.y) {
						batch.draw(brickImage, brick.x, brick.y, brick.width / 2.0f, brick.height / 2.0f, brick.width, brick.height, 1, 1, r);
					}
					if (brick.x + width / 2 < touchPos.x && brick.y + height / 2 < touchPos.y || brick.x + width / 2 > touchPos.x && brick.y + height / 2 > touchPos.y) {
						batch.draw(brickImage, brick.x, brick.y, brick.width / 2.0f, brick.height / 2.0f, brick.width, brick.height, 1, 1, r2);
					}
				}
			}
			batch.end();

			//=============================================== moove + difference with touch
			j = 0;
			Iterator<Rectangle> iter = bricks.iterator();
			while(iter.hasNext()){
				Rectangle brick = iter.next();

				brick.x += ((oldBricks.get(j).x + width/2 - touchPos.x) / differnce(oldBricks.get(j).x + width/2, oldBricks.get(j).y + height/2, touchPos.x, touchPos.y)) * 5;
				brick.y += ((oldBricks.get(j).y + height/2 - touchPos.y) / differnce(oldBricks.get(j).x + width/2, oldBricks.get(j).y + height/2, touchPos.x, touchPos.y)) * 5;


				if (brick.y + height < 0 || brick.y > 480 || brick.x + width < 0 || brick.x > 800) {
					iter.remove();
					oldBricks.removeIndex(j);
					j--;
				}
				j++;

			}
		}

	}

	public double differnce(float x1, float y1, float x2, float y2){
		return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}

	////////////////////////////////////////////set norm parameters width & height
	private void quantity(String word){
		length = word.length();
		increase/=length;
		width = (float) ((camera.viewportWidth - indent) / (length * 3.5));
		height = width/2;

		for(int i = 0; i < name.length(); i++) {
			ch = name.charAt(i);
			if (ch == 'b' || ch == 'B') BLetter();
			if (ch == 'r' || ch == 'R') rLetter();
			if (ch == 'i' || ch == 'I') iLetter();
			if (ch == 'c' || ch == 'C') cLetter();
			if (ch == 'k' || ch == 'K') kLetter();
			if (ch == 'o' || ch == 'O') oLetter();
			if (ch == 'n' || ch == 'N') nLetter();
			if (ch == 'z' || ch == 'Z') zLetter();
			if (ch == 'a' || ch == 'A') aLetter();
			if (ch == 't' || ch == 'T') tLetter();
		}

	}

	////////////////////////////////////////////////////////////////////////data base coords letters
	private void BLetter(){
	//if (layer == 1){
		layer1.set(layer1.size-1, layer1.get(layer1.size-1));
		layer1.add(layer1.get(layer1.size-1) + width);
		layer1.add(layer1.get(layer1.size-1) + width);
		layer1.add(layer1.get(layer1.size-1) + height + width);
	//}
	//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1));
		layer2.add(layer2.get(layer2.size-1) + width + width);
		layer2.add(layer2.get(layer2.size-1) + height + width);
	//}
	//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1));
		layer3.add(layer3.get(layer3.size-1) + width + width);
		layer3.add(layer3.get(layer3.size-1) + height + width);
	//}
	//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1));
		layer4.add(layer4.get(layer4.size-1) + width + height);
		layer4.add(layer4.get(layer4.size-1) + width + width);
	//}
	//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1));
		layer5.add(layer5.get(layer5.size-1) + width);
		layer5.add(layer5.get(layer5.size-1) + width + height + width);
	//}
	//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1));
		layer6.add(layer6.get(layer6.size-1) + width + height);
		layer6.add(layer6.get(layer6.size-1) + width + width);
	//}
	//if (layer == 7){
		layer7.set(layer7.size-1, layer7.get(layer7.size-1));
		layer7.add(layer7.get(layer7.size-1) + width + width);
		layer7.add(layer7.get(layer7.size-1) + height + width);
	//}
	//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1));
		layer8.add(layer8.get(layer8.size-1) + width + height);
		layer8.add(layer8.get(layer8.size-1) + width + width);
	//}
	//if (layer == 9){
		layer9.set(layer9.size-1, layer9.get(layer9.size-1));
		layer9.add(layer9.get(layer9.size-1) + width);
		layer9.add(layer9.get(layer9.size-1) + width + height + width);
	//}
	}

	private void rLetter(){
		//if (layer == 1){
		layer1.set(layer1.size - 1, layer1.get(layer1.size - 1));
		layer1.add(layer1.get(layer1.size-1) + height + width + width + width);
		//}
		//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1));
		layer2.add(layer2.get(layer2.size-1) + height + width + width + width);
		//}
		//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1));
		layer3.add(layer3.get(layer3.size-1) + height + width + width + width);
		//}
		//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1));
		layer4.add(layer4.get(layer4.size-1) + width + width + width + height);
		//}
		//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1) + height);
		layer5.add(layer5.get(layer5.size-1) + width + width + width);
		//}
		//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1));
		layer6.add(layer6.get(layer6.size - 1) + width);
		layer6.add(layer6.get(layer6.size-1) + width + height + width);
		//}
		//if (layer == 7){
		layer7.set(layer7.size - 1, layer7.get(layer7.size - 1));
		layer7.add(layer7.get(layer7.size - 1) + width + height);
		layer7.add(layer7.get(layer7.size - 1) + width + width);
		//}
		//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1) + width + width + width + height);
		//}
		//if (layer == 9){
		layer9.set(layer9.size - 1, layer9.get(layer9.size - 1) + width + width + width + height);
		//}
	}

	private void iLetter(){
		//if (layer == 1){
		layer1.set(layer1.size - 1, layer1.get(layer1.size - 1) + width);
		layer1.add(layer1.get(layer1.size-1) + height + width + width);
		//}
		//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1) + width);
		layer2.add(layer2.get(layer2.size-1) + height + width + width);
		//}
		//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1) + width);
		layer3.add(layer3.get(layer3.size-1) + height + width + width);
		//}
		//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1) + width);
		layer4.add(layer4.get(layer4.size-1) + width + width + height);
		//}
		//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1)  + width);
		layer5.add(layer5.get(layer5.size-1) + width + width + height);
		//}
		//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1) + height);
		layer6.add(layer6.get(layer6.size - 1) + width);
		layer6.add(layer6.get(layer6.size-1) + width + width);
		//}
		//if (layer == 7){
		layer7.set(layer7.size - 1, layer7.get(layer7.size - 1));
		layer7.add(layer7.get(layer7.size - 1) + width + width);
		layer7.add(layer7.get(layer7.size - 1) + height + width);
		//}
		//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1)+ height);
		layer8.add(layer8.get(layer8.size - 1) + width);
		layer8.add(layer8.get(layer8.size - 1) + width + width);
		//}
		//if (layer == 9){
		layer9.set(layer9.size - 1, layer9.get(layer9.size - 1) + width + width + width + height);
		//}
	}

	private void cLetter() {
		//if (layer == 1){
		layer1.set(layer1.size - 1, layer1.get(layer1.size - 1) + height + width);
		layer1.add(layer1.get(layer1.size - 1) + width + width);
		//}
		//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1) + width);
		layer2.add(layer2.get(layer2.size - 1) + width + width + height);
		//}
		//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1) + height);
		layer3.add(layer3.get(layer3.size - 1) + width + width + width);
		//}
		//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1));
		layer4.add(layer4.get(layer4.size - 1) + width + width + width + height);
		//}
		//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1) + height);
		layer5.add(layer5.get(layer5.size - 1) + width + width + width);
		//}
		//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1) + width);
		layer6.add(layer6.get(layer6.size - 1) + width + width + height);
		//}
		//if (layer == 7){
		layer7.set(layer7.size - 1, layer7.get(layer7.size - 1) + height + width);
		layer7.add(layer7.get(layer7.size - 1) + width + width);
		//}
		//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1) + width + width + width + height);
		//}
		//if (layer == 9){
		layer9.set(layer9.size - 1, layer9.get(layer9.size - 1) + width + width + width + height);
		//}
	}


	private void kLetter(){
		//if (layer == 1){
		layer1.set(layer1.size-1, layer1.get(layer1.size-1));
		layer1.add(layer1.get(layer1.size - 1) + width + width);
		layer1.add(layer1.get(layer1.size-1) + height + width);
		//}
		//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1));
		layer2.add(layer2.get(layer2.size - 1) + width + height);
		layer2.add(layer2.get(layer2.size-1) + width + width);
		//}
		//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1));
		layer3.add(layer3.get(layer3.size - 1) + width);
		layer3.add(layer3.get(layer3.size-1) + height + width + width);
		//}
		//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1));
		layer4.add(layer4.get(layer4.size - 1) + width);
		layer4.add(layer4.get(layer4.size-1) + width + width + height);
		//}
		//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1));
		layer5.add(layer5.get(layer5.size - 1) + width + height);
		layer5.add(layer5.get(layer5.size-1) + width + width);
		//}
		//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1));
		layer6.add(layer6.get(layer6.size - 1) + width + width);
		layer6.add(layer6.get(layer6.size-1) + width + height);
		//}
		//if (layer == 7){
		layer7.set(layer7.size - 1, layer7.get(layer7.size - 1));
		layer7.add(layer7.get(layer7.size - 1) + width + width + width + height);
		//}
		//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1));
		layer8.add(layer8.get(layer8.size - 1) + width + width + width + height);
		//}
		//if (layer == 9){
		layer9.set(layer9.size - 1, layer9.get(layer9.size - 1));
		layer9.add(layer9.get(layer9.size - 1) + width + width + width + height);
		//}
	}


	private void oLetter(){
		//if (layer == 1){
		layer1.set(layer1.size - 1, layer1.get(layer1.size - 1) + height);
		layer1.add(layer1.get(layer1.size-1) + width);
		layer1.add(layer1.get(layer1.size-1) + width + width);
		//}
		//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1));
		layer2.add(layer2.get(layer2.size-1) + width + width);
		layer2.add(layer2.get(layer2.size-1) + height + width);
		//}
		//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1));
		layer3.add(layer3.get(layer3.size-1) + width + width);
		layer3.add(layer3.get(layer3.size-1) + height + width);
		//}
		//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1));
		layer4.add(layer4.get(layer4.size-1) + width + width);
		layer4.add(layer4.get(layer4.size-1) + height + width);
		//}
		//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1));
		layer5.add(layer5.get(layer5.size-1) + width + width);
		layer5.add(layer5.get(layer5.size-1) + height + width);
		//}
		//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1));
		layer6.add(layer6.get(layer6.size-1) + width + width);
		layer6.add(layer6.get(layer6.size-1) + height + width);
		//}
		//if (layer == 7){
		layer7.set(layer7.size-1, layer7.get(layer7.size-1) + height);
		layer7.add(layer7.get(layer7.size - 1) + width);
		layer7.add(layer7.get(layer7.size-1) + width + width);
		//}
		//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1) + width + width + width + height);
		//}
		//if (layer == 9){
		layer9.set(layer9.size - 1, layer9.get(layer9.size - 1) + width + width + width + height);
		//}
	}

	private void nLetter(){
		//if (layer == 1){
		layer1.set(layer1.size - 1, layer1.get(layer1.size - 1));
		layer1.add(layer1.get(layer1.size-1) + width + width);
		layer1.add(layer1.get(layer1.size-1) + height + width);
		//}
		//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1));
		layer2.add(layer2.get(layer2.size-1) + width + width);
		layer2.add(layer2.get(layer2.size-1) + height + width);
		//}
		//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1));
		layer3.add(layer3.get(layer3.size-1) + width + width);
		layer3.add(layer3.get(layer3.size-1) + height + width);
		//}
		//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1));
		layer4.add(layer4.get(layer4.size-1) + width + width);
		layer4.add(layer4.get(layer4.size-1) + height + width);
		//}
		//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1));
		layer5.add(layer5.get(layer5.size-1) + width + height);
		layer5.add(layer5.get(layer5.size-1) + width + width);
		//}
		//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1));
		layer6.add(layer6.get(layer6.size-1) + width);
		layer6.add(layer6.get(layer6.size-1) + height + width + width);
		//}
		//if (layer == 7){
		layer7.set(layer7.size - 1, layer7.get(layer7.size - 1));
		layer7.add(layer7.get(layer7.size-1) + width + width + width + height);
		//}
		//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1) + width + width + width + height);
		//}
		//if (layer == 9){
		layer9.set(layer9.size - 1, layer9.get(layer9.size - 1) + width + width + width + height);
		//}
	}

	private void zLetter() {
		//if (layer == 1){
		layer1.set(layer1.size - 1, layer1.get(layer1.size - 1));
		layer1.add(layer1.get(layer1.size - 1) + width);
		layer1.add(layer1.get(layer1.size - 1) + width);
		layer1.add(layer1.get(layer1.size - 1) + width + height);
		//}
		//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1));
		layer2.add(layer2.get(layer2.size - 1) + width);
		layer2.add(layer2.get(layer2.size - 1) + width + width + height);
		//}
		//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1));
		layer3.add(layer3.get(layer3.size - 1) + width + width + width + height);
		//}
		//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1) + height);
		layer4.add(layer4.get(layer4.size - 1) + width + width + width);
		//}
		//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1) + width);
		layer5.add(layer5.get(layer5.size - 1) + width + width + height);
		//}
		//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1) + height);
		layer6.add(layer6.get(layer6.size - 1) + width);
		layer6.add(layer6.get(layer6.size - 1) + width + width);
		//}
		//if (layer == 7){
		layer7.set(layer7.size - 1, layer7.get(layer7.size - 1));
		layer7.add(layer7.get(layer7.size - 1) + width);
		layer7.add(layer7.get(layer7.size - 1) + width);
		layer7.add(layer7.get(layer7.size - 1) + width + height);
		//}
		//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1) + width + width + width + height);
		//}
		//if (layer == 9){
		layer9.set(layer9.size - 1, layer9.get(layer9.size - 1) + width + width + width + height);
		//}
	}

	private void aLetter(){
		//if (layer == 1){
		layer1.set(layer1.size - 1, layer1.get(layer1.size - 1));
		layer1.add(layer1.get(layer1.size-1) + width);
		layer1.add(layer1.get(layer1.size-1) + width);
		layer1.add(layer1.get(layer1.size-1) + width + height);
		//}
		//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1));
		layer2.add(layer2.get(layer2.size-1) + width + width);
		layer2.add(layer2.get(layer2.size-1) + height + width);
		//}
		//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1));
		layer3.add(layer3.get(layer3.size - 1) + width + width);
		layer3.add(layer3.get(layer3.size-1) + height + width);
		//}
		//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1));
		layer4.add(layer4.get(layer4.size - 1) + width + width);
		layer4.add(layer4.get(layer4.size-1) + height + width);
		//}
		//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1) + height);
		layer5.add(layer5.get(layer5.size - 1) + width + height);
		layer5.add(layer5.get(layer5.size-1) + height + width);
		//}
		//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1) + width);
		layer6.add(layer6.get(layer6.size-1) + width);
		layer6.add(layer6.get(layer6.size - 1) + height + width);
		//}
		//if (layer == 7){
		layer7.set(layer7.size - 1, layer7.get(layer7.size - 1) + width + width);
		layer7.add(layer7.get(layer7.size-1) + width + height);
		//}
		//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1) + width + width + width + height);
		//}
		//if (layer == 9){
		layer9.set(layer9.size - 1, layer9.get(layer9.size - 1) + width + width + width + height);
		//}
	}

	private void tLetter(){
		//if (layer == 1){
		layer1.set(layer1.size - 1, layer1.get(layer1.size - 1) + height);
		layer1.add(layer1.get(layer1.size - 1) + width);
		layer1.add(layer1.get(layer1.size - 1) + width + width);
		//}
		//if (layer == 2){
		layer2.set(layer2.size - 1, layer2.get(layer2.size - 1) + height);
		layer2.add(layer2.get(layer2.size-1) + width + width + width);
		//}
		//if (layer == 3){
		layer3.set(layer3.size - 1, layer3.get(layer3.size - 1) + height);
		layer3.add(layer3.get(layer3.size-1) + width + width + width);
		//}
		//if (layer == 4){
		layer4.set(layer4.size - 1, layer4.get(layer4.size - 1) + height);
		layer4.add(layer4.get(layer4.size-1) + width + width + width);
		//}
		//if (layer == 5){
		layer5.set(layer5.size - 1, layer5.get(layer5.size - 1));
		layer5.add(layer5.get(layer5.size-1) + width);
		layer5.add(layer5.get(layer5.size-1) + width + width + height);
		//}
		//if (layer == 6){
		layer6.set(layer6.size - 1, layer6.get(layer6.size - 1) + height);
		layer6.add(layer6.get(layer6.size-1) + width + width + width);
		//}
		//if (layer == 7){
		layer7.set(layer7.size - 1, layer7.get(layer7.size - 1) + height);
		layer7.add(layer7.get(layer7.size - 1) + width + width + width);
		//}
		//if (layer == 8){
		layer8.set(layer8.size - 1, layer8.get(layer8.size - 1) + height);
		layer8.add(layer8.get(layer8.size - 1) + width + width + width);
		//}
		//if (layer == 9){
		layer9.set(layer9.size - 1, layer9.get(layer9.size - 1) + width + width + width + height);
		//}
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}
